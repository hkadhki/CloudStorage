CREATE TABLE Files(
                      id SERIAL PRIMARY KEY,
                      file_name VARCHAR(255),
                      file_type VARCHAR(255),
                      size int,
                      file oid,
                      users_id int,
                      FOREIGN KEY(users_id) REFERENCES users(id)
);