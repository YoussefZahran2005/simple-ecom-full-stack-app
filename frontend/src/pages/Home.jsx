import { useEffect, useState } from "react";
import Header from "../components/layout/Header";
import ProductGrid from "../components/product/ProductGrid";
import { getAllProducts, getProductsByCategory } from "../api/products";
import { getAllCategories } from "../api/categories";

export default function Home() {
  const [categories, setCategories] = useState([]);
  const [products, setProducts] = useState([]);
  const [activeCategory, setActiveCategory] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    getAllCategories()
      .then(setCategories)
      .catch((err) => setError(err.message));
  }, []);

  useEffect(() => {
    setLoading(true);
    const fetchProducts = activeCategory
      ? getProductsByCategory(activeCategory)
      : getAllProducts();

    fetchProducts
      .then(setProducts)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, [activeCategory]);

  if (error) {
    return <p className="page-error">Something went wrong: {error}</p>;
  }

  return (
    <>
      <Header
        categories={categories}
        activeCategory={activeCategory}
        onSelectCategory={setActiveCategory}
      />
      {loading ? (
        <p className="page-loading">Loading products…</p>
      ) : (
        <ProductGrid products={products} />
      )}
    </>
  );
}