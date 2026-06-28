
CREATE EXTENSION IF NOT EXISTS "pgcrypto";


INSERT INTO participants (first_name, last_name, middle_name)
SELECT 'Иван', 'Иванов', 'Иванович'
WHERE NOT EXISTS (SELECT 1 FROM participants WHERE id = 1);

INSERT INTO participants (first_name, last_name, middle_name)
SELECT 'Пётр', 'Петров', 'Петрович'
WHERE NOT EXISTS (SELECT 1 FROM participants WHERE id = 2);

INSERT INTO participants (first_name, last_name, middle_name)
SELECT 'Сидор', 'Сидоров', 'Сидорович'
WHERE NOT EXISTS (SELECT 1 FROM participants WHERE id = 3);

INSERT INTO participants (first_name, last_name, middle_name)
SELECT 'Анна', 'Смирнова', 'Алексеевна'
WHERE NOT EXISTS (SELECT 1 FROM participants WHERE id = 4);

INSERT INTO participants (first_name, last_name, middle_name)
SELECT 'Елена', 'Кузнецова', 'Владимировна'
WHERE NOT EXISTS (SELECT 1 FROM participants WHERE id = 5);

INSERT INTO participants (first_name, last_name, middle_name)
SELECT 'Михаил', 'Михайлов', 'Михайлович'
WHERE NOT EXISTS (SELECT 1 FROM participants WHERE id = 6);

INSERT INTO participants (first_name, last_name, middle_name)
SELECT 'Ольга', 'Орлова', 'Игоревна'
WHERE NOT EXISTS (SELECT 1 FROM participants WHERE id = 7);

INSERT INTO participants (first_name, last_name, middle_name)
SELECT 'Дмитрий', 'Дмитриев', 'Дмитриевич'
WHERE NOT EXISTS (SELECT 1 FROM participants WHERE id = 8);

INSERT INTO participants (first_name, last_name, middle_name)
SELECT 'Наталья', 'Новикова', 'Павловна'
WHERE NOT EXISTS (SELECT 1 FROM participants WHERE id = 9);

INSERT INTO participants (first_name, last_name, middle_name)
SELECT 'Сергей', 'Сергеев', 'Сергеевич'
WHERE NOT EXISTS (SELECT 1 FROM participants WHERE id = 10);


INSERT INTO qr_codes (participant_id, qr_uuid)
SELECT 1, gen_random_uuid()
WHERE NOT EXISTS (SELECT 1 FROM qr_codes WHERE participant_id = 1);

INSERT INTO qr_codes (participant_id, qr_uuid)
SELECT 2, gen_random_uuid()
WHERE NOT EXISTS (SELECT 1 FROM qr_codes WHERE participant_id = 2);

INSERT INTO qr_codes (participant_id, qr_uuid)
SELECT 3, gen_random_uuid()
WHERE NOT EXISTS (SELECT 1 FROM qr_codes WHERE participant_id = 3);

INSERT INTO qr_codes (participant_id, qr_uuid)
SELECT 4, gen_random_uuid()
WHERE NOT EXISTS (SELECT 1 FROM qr_codes WHERE participant_id = 4);

INSERT INTO qr_codes (participant_id, qr_uuid)
SELECT 5, gen_random_uuid()
WHERE NOT EXISTS (SELECT 1 FROM qr_codes WHERE participant_id = 5);

INSERT INTO qr_codes (participant_id, qr_uuid)
SELECT 6, gen_random_uuid()
WHERE NOT EXISTS (SELECT 1 FROM qr_codes WHERE participant_id = 6);

INSERT INTO qr_codes (participant_id, qr_uuid)
SELECT 7, gen_random_uuid()
WHERE NOT EXISTS (SELECT 1 FROM qr_codes WHERE participant_id = 7);

INSERT INTO qr_codes (participant_id, qr_uuid)
SELECT 8, gen_random_uuid()
WHERE NOT EXISTS (SELECT 1 FROM qr_codes WHERE participant_id = 8);

INSERT INTO qr_codes (participant_id, qr_uuid)
SELECT 9, gen_random_uuid()
WHERE NOT EXISTS (SELECT 1 FROM qr_codes WHERE participant_id = 9);

INSERT INTO qr_codes (participant_id, qr_uuid)
SELECT 10, gen_random_uuid()
WHERE NOT EXISTS (SELECT 1 FROM qr_codes WHERE participant_id = 10);