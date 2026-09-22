import request from "./client";

export function getAllCategories() {
  return request("/categories");
}