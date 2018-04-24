package cn.edu.pku.ss.crypto.abe.apiV2;

import java.io.File;

public class Example {
	public static void main(String[] args) {
		Server server = new Server();
		Client PKUClient = new Client(new String[]{"PKU", "Student"});
		Client THUClient = new Client(new String[]{"THU", "Student"});
		Client TeacherClient = new Client(new String[]{"PKU", "Teacher"});
		//client��server����ȡ��Կ�ַ���
		String PKJSONString = server.getPublicKeyInString();
		PKUClient.setPK(PKJSONString);
		THUClient.setPK(PKJSONString);
		TeacherClient.setPK(PKJSONString);

		//client���Լ���������Ϣ���͸�server,����ȡ˽Կ�ַ���
		String SKJSONString = server.generateSecretKey(PKUClient.getAttrs());
		PKUClient.setSK(SKJSONString);
		
		SKJSONString = server.generateSecretKey(THUClient.getAttrs());
		THUClient.setSK(SKJSONString);
		
		SKJSONString = server.generateSecretKey(TeacherClient.getAttrs());
		TeacherClient.setSK(SKJSONString);
		
		//����
		String outputFileName = "test.cpabe";
		File in = new File("README.md");
		String policy = "Student OR Teacher";
		PKUClient.enc(in, policy, outputFileName);
		
		//����
		in = new File(outputFileName);
//		THUClient.dec(in);
		TeacherClient.dec(in);
	}
}