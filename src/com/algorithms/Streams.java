package com.algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Streams {
	public static void main(String[] args) {

		String s = "Hello world!";
		int i = 143141141;
		try {
			// create new file with an ObjectOutputStream
			FileOutputStream out = new FileOutputStream("/home/doom/workspace/Examples/src/com/example/test.txt");
			ObjectOutputStream oout = new ObjectOutputStream(out);

			// write something in a file
			oout.writeObject(s);
			oout.writeObject(i);
			// close the stream
			oout.close();

			// create an ObjectInputStream for the file we created before
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					"test.txt"));
			// read and print what we wrote before
			System.out.println("" + (String) ois.readObject());
			System.out.println("" + ois.readObject());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
