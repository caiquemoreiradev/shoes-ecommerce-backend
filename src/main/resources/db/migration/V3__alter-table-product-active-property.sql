ALTER TABLE product
DROP COLUMN is_active;

ALTER TABLE product ADD active BOOLEAN;
UPDATE product SET active = true;