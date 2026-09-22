export default function Header({ categories, activeCategory, onSelectCategory }) {
  return (
    <header className="header">
      <span className="header__brand">zahranStore</span>

      <nav className="header__nav">
        <button
          className={activeCategory === null ? "nav-link nav-link--active" : "nav-link"}
          onClick={() => onSelectCategory(null)}
        >
          All
        </button>

        {categories.map((category) => (
          <button
            key={category.id}
            className={activeCategory === category.id ? "nav-link nav-link--active" : "nav-link"}
            onClick={() => onSelectCategory(category.id)}
          >
            {category.name}
          </button>
        ))}
      </nav>
    </header>
  );
}