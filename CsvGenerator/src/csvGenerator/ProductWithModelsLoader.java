package csvGenerator;

import csvGenerator.model.Product;

public interface ProductWithModelsLoader {
	Product loadModel(Product p, String line, String separator);
}
