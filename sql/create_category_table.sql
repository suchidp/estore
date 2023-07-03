CREATE TABLE category (
  categoryId INT PRIMARY KEY AUTO_INCREMENT,
  category_name VARCHAR(255),
  created_on TIMESTAMP,
  last_updated_on TIMESTAMP,
  archive BOOLEAN
);