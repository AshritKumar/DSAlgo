package practice.multThreading;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.imageio.IIOException;

public class FixedThreadPoolEx {
	
	class ScanDirs implements Callable<List<String>>{
		String dir;
		Set<String> extns;
		List<String> flist = new ArrayList<>();
		boolean recursive = true;
		public ScanDirs(String dir, Set<String> extns) {
			this.dir = dir;
			this.extns = extns;
		}
		public ScanDirs(String dir, Set<String> extns, boolean recursive) {
			this.dir = dir;
			this.extns = extns;
			this.recursive = recursive;
		}
		
		public void run() {
			File fol = new File(dir);
			if(! fol.isDirectory()) {
				try {
					throw new IIOException("Given path is not a folder");
				} catch (IIOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println(Thread.currentThread().getName()+" : scanning directory "+ dir+"\n");
				ScanDirectories(fol, extns);
			}
		}
		
		public void ScanDirectories(File file, Set<String> extns) {
			File[] files = file.listFiles();
			if(files != null) {
				for(File f : files) {
					if(f.isDirectory()) {
						if(recursive)
							ScanDirectories(f, extns);
					}
					String fname = f.getName();
					int i = fname.lastIndexOf('.');
					if(i > 0) {
						//System.out.println(fname.substring(fname.lastIndexOf('.')+1, fname.length()));
						if(extns.contains(fname.substring(fname.lastIndexOf('.')+1, fname.length()))) {
							System.out.println(Thread.currentThread().getName()+" ::: "+dir+" - "+f.getName());
							flist.add(Thread.currentThread().getName()+" ::: "+dir+" - "+f.getName());
						}
					}
				}
			}
		}
		@Override
		public List<String> call() throws Exception {
			File fol = new File(dir);
			if(! fol.isDirectory()) {
				try {
					throw new IIOException("Given path is not a folder");
				} catch (IIOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println(Thread.currentThread().getName()+" : scanning directory "+ dir+"\n");
				ScanDirectories(fol, extns);
			}
			return flist;
		}
		
	}
	public void scanDirectoriesForJava() {
		String[] dirList = new String[]{"C:\\Program Files", "C:\\ProgramData", "C:\\Program Files (x86)","C:\\Windows","C:\\oraclexe"};
		Set<String> extns = new HashSet<>();
		extns.add("java");
		//ExecutorService ex = Executors.newFixedThreadPool(3);
		ExecutorService ex = Executors.newCachedThreadPool();
		//extns.add("png");
		List<Future> fl = new ArrayList<>();
		for(String dir : dirList) {
			Future<List<String>> f = ex.submit(new ScanDirs(dir, extns, true));
			fl.add(f);
		}
		for(Future<List<String>> fu : fl) {
			try {
				System.out.println(fu.get(10, TimeUnit.SECONDS));
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				e.printStackTrace();
			}
		}
		System.out.println("***********");
	}
	
	public static void main(String[] args) {
		FixedThreadPoolEx fex = new FixedThreadPoolEx();
		fex.scanDirectoriesForJava();
	}

}
