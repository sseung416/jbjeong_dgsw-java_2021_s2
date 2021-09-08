package kr.hs.dgsw.javaClass.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class BufferStudy {

	public static void main(String[] args) {
		try {
			studyBuffer();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void studyBuffer() throws Exception {
		
		ByteBuffer buffer1 = ByteBuffer.allocate(10);
		
		byte[] bytes = new byte[] {1, 2, 3, 4, 5};
		ByteBuffer buffer2 = ByteBuffer.wrap(bytes);
		
		printStatus(buffer1, "buffer1 ����");
		printStatus(buffer2, "buffer2 ����");
		
		// buffer1�� �߰��Ѵ�.
		buffer1.put((byte)55);
		buffer1.put((byte)-12);
		printStatus(buffer1, "buffer1�� 2byte �߰�");
		
		buffer1.put(new byte[] {15, 16, 17, 18});
		printStatus(buffer1, "buffer1�� 4byte �߰�");

		byte value = buffer2.get();
		System.out.println("���� �� : " + value);
		printStatus(buffer2, "buffer2������ 1byte ����");

		byte[] bytes1 = new byte[3];
		buffer2.get(bytes1);
		printStatus(buffer2, "buffer2������ 3byte ����");

		printStatus(buffer1, "buffer1 flip() ���� ��");
		buffer1.flip();
		printStatus(buffer1, "buffer1 flip() ���� ��");
		value = buffer1.get();
		System.out.println("���� �� : " + value);
		printStatus(buffer1, "buffer1������ 1byte ����");
		
		printStatus(buffer1, "buffer1 rewind() ���� ��");
		buffer1.rewind();
		printStatus(buffer1, "buffer1 rewind() ���� ��");

		/*
		printStatus(buffer1, "buffer1 clear() ���� ��");
		buffer1.clear();
		printStatus(buffer1, "buffer1 clear() ���� ��");
		*/
		
		buffer1.get(bytes1);
		printStatus(buffer1, "buffer1 ����");
		buffer1.mark();
		
		buffer1.get();
		printStatus(buffer1, "buffer1 reset() ���� ��");
		buffer1.reset();
		printStatus(buffer1, "buffer1 reset() ���� ��");
		
		printStatus(buffer1, "buffer1 compact() ���� ��");
		buffer1.compact();
		printStatus(buffer1, "buffer1 compact() ���� ��");
		buffer1.rewind();
		printStatus(buffer1, "buffer1 rewind() ���� ��");
		value = buffer1.get();
		System.out.println("���� �� : " + value);
		
	}
	
	public static void printStatus(Buffer buffer, String note) {
		System.out.println(
				String.format("%s : %d %d %d", 
						note,
						buffer.position(),
						buffer.limit(),
						buffer.capacity()));
	}
	
	
	public static void studyBuffer1() throws Exception {
		System.out.println("Buffer ����");

		byte[] bytes = new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		// ���۸� �Ҵ��Ѵ�.
		ByteBuffer buffer1 = ByteBuffer.allocate(100);
		
		// �迭�� ���� buffer�� �ִ´�.
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		
		// ���ڿ�
		CharBuffer charBuffer = CharBuffer.wrap("Hello world");
		
		
		System.out.println(String.format("buffer ���� �뷮 : %d", buffer.capacity()));
		System.out.println(String.format("buffer1 ���� �뷮 : %d", buffer1.capacity()));
		System.out.println(String.format("charBuffer ���� �뷮 : %d", charBuffer.capacity()));
		
		// position, limit, capacity, mark
		System.out.println(String.format("position : %d", buffer1.position()));
		System.out.println(String.format("limit : %d", buffer1.limit()));
		System.out.println(String.format("capacity : %d", buffer1.capacity()));
		//System.out.println(String.format("mark : %d", buffer.mark()));

		System.out.println("buffer1�� 2byte�� �߰��մϴ�.");
		// buffer1 �� 2byte�� �����Ѵ�.
		buffer1.put((byte)20);
		buffer1.put((byte)30);

		System.out.println(String.format("position : %d", buffer1.position()));
		System.out.println(String.format("limit : %d", buffer1.limit()));
		System.out.println(String.format("capacity : %d", buffer1.capacity()));

		// �о� ���ô�.
		System.out.println("flip");
		buffer1.flip();
		System.out.println(String.format("position : %d", buffer1.position()));
		System.out.println(String.format("limit : %d", buffer1.limit()));
		System.out.println(String.format("capacity : %d", buffer1.capacity()));
		
		byte value = buffer1.get();
		System.out.println(String.format("���� : %d", value));
		System.out.println(String.format("position : %d", buffer1.position()));
		System.out.println(String.format("limit : %d", buffer1.limit()));
		System.out.println(String.format("capacity : %d", buffer1.capacity()));
		
		// rewind
		// clear
		// compact
		
		System.out.println("------------------------------------------------");
		System.out.println("");
	}

	
	
}
