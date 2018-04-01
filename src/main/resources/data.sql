INSERT INTO role(name) VALUES ('GUEST');
INSERT INTO role(name) VALUES ('USER');
INSERT INTO role(name) VALUES ('ADMIN');

INSERT INTO status(status_name) VALUES ('New');
INSERT INTO status(status_name) VALUES ('Assigned');
INSERT INTO status(status_name) VALUES ('In Progress');
INSERT INTO status(status_name) VALUES ('Resolved');
INSERT INTO status(status_name) VALUES ('Closed');
INSERT INTO status(status_name) VALUES ('Reopened');

INSERT INTO user(email, first_name, last_name, username, password) VALUES ('squidward@tut.by','Squidward', 'Tentacles','guest', '$2a$10$1jMOaNXVtJthe14vhFH1pe4X4CyxDd7CVPZXktxsnaHxVBw.wmVmu');
INSERT INTO user(email, first_name, last_name, username, password) VALUES ('star@tut.by','Patrick', 'Star','user', '$2a$10$1jMOaNXVtJthe14vhFH1pe4X4CyxDd7CVPZXktxsnaHxVBw.wmVmu');
INSERT INTO user(email, first_name, last_name, username, password) VALUES ('spongebob@mail.ru','SpongeBob', 'SquarePants','admin', '$2a$10$1jMOaNXVtJthe14vhFH1pe4X4CyxDd7CVPZXktxsnaHxVBw.wmVmu');

INSERT INTO users_roles(user_id, role_id) VALUES ('1','1');
INSERT INTO users_roles(user_id, role_id) VALUES ('2','2');
INSERT INTO users_roles(user_id, role_id) VALUES ('3','3');

INSERT INTO project(description, project_name, user_id) VALUES ('Quo et noster qualisque','Maiorum','1');
INSERT INTO project(description, project_name, user_id) VALUES ('Id est latine scripta ','Primis', '2');
INSERT INTO project(description, project_name, user_id) VALUES ('Ad ridens molestiae','Legimus','3');
INSERT INTO project(description, project_name, user_id) VALUES ('His nulla erroribus','Vivendum','1');
INSERT INTO project(description, project_name, user_id) VALUES ('Cu ignota primis', 'Nostrum','3');

INSERT INTO issue(create_date,description,summary, assignee_id,created_by_id,
                  project_id,status_id)
VALUES ('18.12.2017','Some description','Sea et nihil altera, ad mollis appetere consectetuer eum',
                     '1','1','1','1');

