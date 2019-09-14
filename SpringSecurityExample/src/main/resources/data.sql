INSERT INTO users (username, password, enabled)
 	values ('lav', 'lav', true);
 
 INSERT INTO users (username, password, enabled)
 	values ('joseph', 'joseph', true);
 	

 INSERT INTO authorities (username, authority)
 	values ('lav', 'ROLE_MANAGER');
 	
 INSERT INTO authorities (username, authority)
 	values ('joseph', 'ROLE_USER');