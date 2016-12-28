	
	package at.fhv.TimeTracker;
	
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.util.Set;
	import java.util.HashSet;
	
	public class UserDao {
		private void saveUserList(Set<User> userList) {
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
		
		public Set<User> getAllUsers() {
		// Start of user code getAllUsers
			Set<User> userList = null;
		      try {
		         File file = new File("Users.dat");
		         if (!file.exists()) {
		            User user = new User(1, "Mahesh", "Teacher");
		            userList = new HashSet<User>();
		            userList.add(user);
		            saveUserList(userList);		
		         }
		         else{
		            FileInputStream fis = new FileInputStream(file);
		            ObjectInputStream ois = new ObjectInputStream(fis);
		            userList = (Set<User>) ois.readObject();
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
		
		// Start of user code (user defined operations)
		
		// End of user code
		
	}
	
