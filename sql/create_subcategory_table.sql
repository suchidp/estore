CREATE TABLE subcategory (
  subCategoryId INTEGER PRIMARY KEY AUTO_INCREMENT,
  sub_category_name VARCHAR(255) NOT NULL,
  category_id INT,
  created_on TIMESTAMP,
  last_updated_on TIMESTAMP,
  archive BOOLEAN,
  FOREIGN KEY (category_id) REFERENCES category (categoryId)
);