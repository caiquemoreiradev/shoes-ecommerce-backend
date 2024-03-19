CREATE TABLE product (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    price FLOAT NOT NULL,
    old_price FLOAT,
    image TEXT NOT NULL,
    on_promo BOOLEAN
);