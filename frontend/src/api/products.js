import request from "./client";

export function getAllProducts() {
  return request("/products");
}

export function getProductsByCategory(categoryId) {
  return request(`/products/category/${categoryId}`);
}