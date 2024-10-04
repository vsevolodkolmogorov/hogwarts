ALTER TABLE student
ADD CONSTRAINT age_constraint CHECK ( age > 16 ),
ALTER COLUMN name SET NOT NULL,
ADD CONSTRAINT name_unique UNIQUE (name),
ALTER COLUMN age INT DEFAULT 20;


ALTER TABLE faculty
ADD CONSTRAINT color_name_constraint UNIQUE (color,name);