USE periodicals;

/*==============================================================*/
/* Added data: values for presentation                          */
/*==============================================================*/

INSERT INTO users(role_id, first_name, last_name, email, password, date_of_birth, gender)
VALUES (1, 'Maksym', 'Svynarhcuk', 'admin@gmail.com', 'admin', '1998-11-27', 'male'),
       (2, 'Ada', 'Lovelace', 'user@gmail.com', 'user', '2000-11-13', 'female');

INSERT INTO periodicals(publisher_id, frequency_id, periodical_type_id, name, price,
                        periodical_description)
VALUES (1, 4, 1, 'Batman', 3, 'Batman is an ongoing American comic book series featuring the DC Comics superhero Batman as its main protagonist. The character first appeared in Detective Comics #27 (cover dated May 1939). Batman proved to be so popular that a self-titled ongoing comic book series began publication with a cover date of Spring 1940. It was first advertised in early April 1940, one month after the first appearance of his new sidekick, Robin, the Boy Wonder. Batman comics have proven to be popular since the 1940s.'),
       (2, 3, 2, 'Time', 2, 'Breaking news and analysis from TIME. Politics, world news, photos, video, tech reviews, health, science and entertainment news.');
