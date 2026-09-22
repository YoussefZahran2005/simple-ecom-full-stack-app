export default function ProductCard({ product }) {
  return (
    <article className="product-card">
      <img
        className="product-card__image"
        src={product.imageUrl}
        alt={product.name}
      />
      <h3 className="product-card__name">{product.name}</h3>
      <p className="product-card__price">${product.price.toFixed(2)}</p>
    </article>
  );
}