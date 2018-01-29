package csvGenerator;

public interface CategoryParser {
	public String getCategoryString(String[] splitted);
	public String[] createCategory(String category);
}
