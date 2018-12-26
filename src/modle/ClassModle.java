package modle;

public class ClassModle {
	
	int classId;
	String className;
	int  votes;
	int image;
	
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public int getVotes() {
		return votes;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
}
