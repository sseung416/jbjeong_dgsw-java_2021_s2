package kr.hs.dgsw.javaClass.nio;

import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Iterator;
import java.util.List;

public class FileStudy {

	public static void studyPath() throws Exception {
		System.out.println("Path ����");
		
		Path path = Paths.get("C:\\dgsw_3", "hello.txt");
		path = Paths.get("C:\\dgsw_3\\hello.txt");
		path = Paths.get("C:", "dgsw_3", "hello.txt");
		
		System.out.println(String.format("���� �̸� : %s", path.getFileName()));
		System.out.println(String.format("�θ� ���丮 : %s", path.getParent().getFileName()));
		System.out.println(String.format("��ø ��� �� : %s", path.getNameCount()));

		System.out.println(String.format("���丮 ���� : %s", Files.isDirectory(path)));
		System.out.println(String.format("���� ���� : %s", Files.isRegularFile(path)));
		System.out.println(String.format("������ ���� �ð� : %s", Files.getLastModifiedTime(path)));
		System.out.println(String.format("���� ũ�� : %s", Files.size(path)));
		System.out.println(String.format("������ : %s", Files.getOwner(path)));
		System.out.println(String.format("���� ���� : %s", Files.isHidden(path)));
		System.out.println(String.format("�б� ���� ���� : %s", Files.isReadable(path)));
		System.out.println(String.format("���� ���� ���� : %s", Files.isWritable(path)));

		
		System.out.println("------------------------------------------------");
		System.out.println("");
	}
	
	public static void studyFileSystem() throws Exception {
		System.out.println("FileSystem ����");
		
		FileSystem fileSystem = FileSystems.getDefault();
		
		Iterator<FileStore> stores = 
				fileSystem.getFileStores().iterator();
		while (stores.hasNext()) {
			FileStore store = stores.next();
			System.out.println(String.format("FileStore : %s", store.toString()));
			System.out.println(String.format("getTotalSpace : %s", store.getTotalSpace()));
			System.out.println(String.format("getUnallocatedSpace : %s", store.getUnallocatedSpace()));
			System.out.println(String.format("getUsableSpace : %s", store.getUsableSpace()));
			System.out.println(String.format("isReadOnly : %s", store.isReadOnly()));
			System.out.println(String.format("name : %s", store.name()));
			System.out.println(String.format("type : %s", store.type()));
		}

		System.out.println("");

		Iterator<Path> paths = fileSystem.getRootDirectories().iterator();
		while (paths.hasNext()) {
			Path path = paths.next();
			System.out.println(String.format("Root Path : %s", path.toString()));
		}

		System.out.println("");
		System.out.println(String.format("seperator : %s", fileSystem.getSeparator()));

		
		System.out.println("------------------------------------------------");
		System.out.println("");
	}
	
	
	public static void studyFileManagement() throws Exception {
		System.out.println("����/���丮 ���� ���� ����");
		
		Path dirPath = Paths.get("C:\\study\\nio", "myDir");
		Path filePath = Paths.get("C:\\study\\nio", "myDir", "myFile.txt");

		if (Files.notExists(dirPath)) {
			Files.createDirectories(dirPath);
		}
		
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}
		
		Path parentPath = Paths.get("C:\\study\\nio");
		DirectoryStream<Path> directoryStream = 
				Files.newDirectoryStream(parentPath);
		
		for (Path path : directoryStream) {
			if (Files.isDirectory(path)) {
				System.out.println(
						String.format("���丮 : %s", 
								path.getFileName()));
			} else {
				System.out.println(
						String.format("���� : %s (%d)", 
								path.getFileName(), 
								Files.size(path)));
			}
		}

		Files.deleteIfExists(filePath);
		Files.deleteIfExists(dirPath);
		
		System.out.println("------------------------------------------------");
		System.out.println("");
	}
	
	public static void studyWatchService() throws Exception {
		System.out.println("Watch Service  ����");
		
		WatchService watchService = 
				FileSystems.getDefault().newWatchService();
		
		Path path = Paths.get("C:\\study\\nio", "subDir");
		path.register(watchService, 
				StandardWatchEventKinds.ENTRY_CREATE, 
				StandardWatchEventKinds.ENTRY_MODIFY, 
				StandardWatchEventKinds.ENTRY_DELETE);
		
		while (true) {
			WatchKey watchKey = watchService.take();
			
			List<WatchEvent<?>> events = watchKey.pollEvents();
			for (WatchEvent<?> event : events) {
				Path eventPath = (Path)event.context();
				Kind<?> kind = event.kind();
				
				if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
					System.out.println(
							String.format("���� %s �� �����Ǿ����ϴ�.", 
									eventPath.getFileName()));
				}
				else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
					System.out.println(
							String.format("���� %s �� �����Ǿ����ϴ�.", 
									eventPath.getFileName()));
				}
				else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
					System.out.println(
							String.format("���� %s �� �����Ǿ����ϴ�.", 
									eventPath.getFileName()));
				}
			}

			boolean valid = watchKey.reset();

			if (!valid) {
				break;
			}
		}
		
		watchService.close();
		
		System.out.println("------------------------------------------------");
		System.out.println("");
	}
	
	
	public static void main(String[] args) {
		try {
			studyPath();
			studyFileSystem();
			studyFileManagement();
			studyWatchService();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
