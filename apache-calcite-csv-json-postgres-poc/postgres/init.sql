CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    method VARCHAR(50),
    total NUMERIC(18, 2)
);

INSERT INTO payments (user_id, method, total) VALUES
(1, 'credit_card', 250),
(2, 'pix', 90),
(3, 'paypal', 400);
