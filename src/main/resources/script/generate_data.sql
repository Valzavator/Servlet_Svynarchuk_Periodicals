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
        'Breaking news and analysis from TIME. Politics, world news, photos, video, tech reviews, health, science and entertainment news.'),
       (2, 6, 2, 'People', 5,
        'People is an American weekly magazine of celebrity and human-interest stories, published by Meredith Corporation. With a readership of 46.6 million adults, People has the largest audience of any American magazine.'),
       (2, 6, 2, 'Game Informer', 5,
        'Game Informer currently reviews games on PCs, PlayStation 4, PlayStation Vita, PlayStation VR, Xbox One, Nintendo Switch, Nintendo 3DS, Android, iOS. Older games, three per issue, were given brief reviews in the magazine''s Classic GI section (compared with the game''s original review score, if one exists). This was discontinued in 2009, months before the redesign of the magazine. The magazine''s staff rate games on a scale of 1 to 10 with quarter point intervals. A score of 1 - 5 is considered terrible; 10 is a rare, "outstanding", nearly perfect game; and 6-7 is "average", a decently, playable, and sometimes fun (but flawed) game.'),
       (2, 1, 3, 'The Guardian', 2,
        'Formerly known as The Manchester Guardian, this newspaper was founded in 1821 by a group of non-conformist businessmen headed by John Edward Taylor. The much-quoted article “comment is free but facts are sacred” is still used to explain the values of the present-day newspaper.'),
       (2, 1, 3, 'The Wall Street Journal', 0.99,
        'In 1882, with 2 associates, newspaperman Charles Henry Dow founded Dow Jones and Company, a news agency for the financial world. The Journal took its modern shape and prominence in the 1940s, a time of industrial expansion for the United States and its financial institutions in New York. The Wall Street Journal is a special paper for people in the business and economic communities, yet it goes far beyond that designation in its treatment of the news.');