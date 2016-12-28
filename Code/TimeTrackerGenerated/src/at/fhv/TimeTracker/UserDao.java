	
	package at.fhv.TimeTracker;
	
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.util.List;
	import java.util.ArrayList;
	
	public class UserDao {
		public List<User> getAllUsers() {
		// Start of user code getAllUsers
			List<User> userList = null;
		      try {
		         File file = new File("Users.dat");
		         if (!file.exists()) {
		            User user = new User(1, "Mahesh", "Teacher");
		            userList = new ArrayList<User>();
		            userList.add(user);
		            saveUserList(userList);		
		         }
		         else{
		            FileInputStream fis = new FileInputStream(file);
		            ObjectInputStream ois = new ObjectInputStream(fis);
		            userList = (List<User>) ois.readObject();
		            ois.close();
		         }
		      } catch (IOException e) {
		         e.printStackTrace();
		      } catch (ClassNotFoundException e) {
		         e.printStackTrace();
		      }		
		      return userList;
		// End of user code
		}
		
		private void saveUserList(List<User> userList) {
		// Start of user code saveUserList
			try {
		         File file = new File("Users.dat");
		         FileOutputStream fos;

		         fos = new FileOutputStream(file);

		         ObjectOutputStream oos = new ObjectOutputStream(fos);
		         oos.writeObject(userList);
		         oos.close();
		      } catch (FileNotFoundException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      }  
		// End of user code
		}
		
		// Start of user code (user defined operations)
		
		// End of user code
		
	}
	
