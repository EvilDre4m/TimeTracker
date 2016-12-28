	
	package at.fhv.TimeTracker;
	
	import java.io.Serializable;
	
	import javax.xml.bind.annotation.XmlElement;
	import javax.xml.bind.annotation.XmlRootElement;
	
	@XmlRootElement(name = "user")
	public class User implements Serializable {
		private static final long serialVersionUID = 1L;
	    /**
	     * Description of the property id.
	     */
	    private Integer id = null;
	    
	    /**
	     * Description of the property name.
	     */
	    private String name = "";
	    
	    /**
	     * Description of the property profession.
	     */
	    private String profession = "";
	    
	    // Start of user code (user defined attributes)
	    
	    // End of user code
	    
	
		public User(){}
		public User(Integer id, String name, String profession)
		{
			this.id = id;
			this.name = name;
			this.profession = profession;
		}
	
		public Integer getId(){
		   return this.id;
		}
		
		@XmlElement
		public void setId(Integer id){
		   this.id = id;
		}
		
		public String getName(){
		   return this.name;
		}
		
		@XmlElement
		public void setName(String name){
		   this.name = name;
		}
		
		public String getProfession(){
		   return this.profession;
		}
		
		@XmlElement
		public void setProfession(String profession){
		   this.profession = profession;
		}
		
	
	}
	
