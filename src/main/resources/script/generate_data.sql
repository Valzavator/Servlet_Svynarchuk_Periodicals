USE periodicals;

/*==============================================================*/
/* Added data: values for presentation                          */
/*==============================================================*/

INSERT INTO users(role_id, first_name, last_name, email, password, date_of_birth, gender)
VALUES (1, 'Maksym', 'Svynarhcuk', 'admin@gmail.com', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '1998-11-27', 'male'),
       (2, 'Ada', 'Lovelace', 'ada.user@gmail.com', '0a041b9462caa4a31bac3567e0b6e6fd9100787db2ab433d96f6d178cabfce90', '2000-11-13', 'female'),
       (2, 'Ann', 'Reader', 'ann.user@gmail.com', '6025d18fe48abd45168528f18a82e265dd98d421a7084aa09f61b341703901a3', '1998-11-13', 'female');

INSERT INTO periodicals(publisher_id, frequency_id, periodical_type_id, periodical_name,
                        periodical_price, periodical_description)
VALUES (1, 4, 1, 'Batman', 3,
        'Batman is an ongoing American comic book series featuring the DC Comics superhero Batman as its main protagonist. The character first appeared in Detective Comics #27 (cover dated May 1939). Batman proved to be so popular that a self-titled ongoing comic book series began publication with a cover date of Spring 1940. It was first advertised in early April 1940, one month after the first appearance of his new sidekick, Robin, the Boy Wonder. Batman comics have proven to be popular since the 1940s.'),
       (2, 3, 2, 'Time', 2,
        'Breaking news and analysis from TIME. Politics, world news, photos, video, tech reviews, health, science and entertainment news.');

INSERT INTO payments(user_id, total_price)
VALUES (2, 100),
       (3, 200);