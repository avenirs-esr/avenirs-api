DROP TABLE IF EXISTS notification;
 
CREATE TABLE notification (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  priority TINYINT NOT NULL,
  type TINYINT NOT NULL,
  audience VARCHAR(2500) DEFAULT '*',
  header VARCHAR(80) DEFAULT NULL,
  body VARCHAR(2500)  DEFAULT NULL,
  footer VARCHAR(80)  DEFAULT NULL
);
 
INSERT INTO notification (priority, type, body) VALUES
  (0, 0, 'The message 0 of the notification'),
  (1, 1, 'The message 1 of the notification');