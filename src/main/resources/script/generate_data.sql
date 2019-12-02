USE periodicals;

/*==============================================================*/
/* Added data: values for presentation                          */
/*==============================================================*/

INSERT INTO users(role_id, first_name, last_name, email, password, date_of_birth, gender)
VALUES (1, 'Maksym', 'Svynarhcuk', 'admin@gmail.com',
        '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '1998-11-27', 'male'),
       (2, 'Ada', 'Lovelace', 'ada.user@gmail.com', '0a041b9462caa4a31bac3567e0b6e6fd9100787db2ab433d96f6d178cabfce90',
        '2000-11-13', 'female'),
       (2, 'Ann', 'Reader', 'ann.user@gmail.com', '6025d18fe48abd45168528f18a82e265dd98d421a7084aa09f61b341703901a3',
        '1998-11-13', 'female');

INSERT INTO periodicals(publisher_id, frequency_id, periodical_type_id, periodical_name,
                        periodical_price, periodical_description)
VALUES (1, 6, 2, 'Scientific American', 6,
        'Scientific American Magazine provides a forum where working scientists and Nobel laureates present their latest work in a broad range of fields, including medicine, technology, energy, the environment, business, and more. Written and edited with technically educated professionals and managers in mind, each issue of Scientific American Magazine provides all the relevant information on how the latest advances apply to industry, the professions, and public policy.'),
       (2, 3, 2, 'Time', 2,
        'Breaking news and analysis from TIME. Politics, world news, photos, video, tech reviews, health, science and entertainment news.'),
       (3, 6, 2, 'People', 5,
        'People is an American weekly magazine of celebrity and human-interest stories, published by Meredith Corporation. With a readership of 46.6 million adults, People has the largest audience of any American magazine.'),
       (4, 6, 2, 'Game Informer', 5,
        'Game Informer currently reviews games on PCs, PlayStation 4, PlayStation Vita, PlayStation VR, Xbox One, Nintendo Switch, Nintendo 3DS, Android, iOS. Older games, three per issue, were given brief reviews in the magazine''s Classic GI section (compared with the game''s original review score, if one exists). This was discontinued in 2009, months before the redesign of the magazine. The magazine''s staff rate games on a scale of 1 to 10 with quarter point intervals. A score of 1 - 5 is considered terrible; 10 is a rare, "outstanding", nearly perfect game; and 6-7 is "average", a decently, playable, and sometimes fun (but flawed) game.'),
       (5, 1, 3, 'The Guardian', 2,
        'Formerly known as The Manchester Guardian, this newspaper was founded in 1821 by a group of non-conformist businessmen headed by John Edward Taylor. The much-quoted article “comment is free but facts are sacred” is still used to explain the values of the present-day newspaper.'),
       (6, 1, 3, 'The Wall Street Journal', 0.99,
        'In 1882, with 2 associates, newspaperman Charles Henry Dow founded Dow Jones and Company, a news agency for the financial world. The Journal took its modern shape and prominence in the 1940s, a time of industrial expansion for the United States and its financial institutions in New York. The Wall Street Journal is a special paper for people in the business and economic communities, yet it goes far beyond that designation in its treatment of the news.'),
       (7, 6, 2, 'Forbes', 3,
        'Forbes Magazine is the preeminent business and finance magazine in the country. Covering a broad range of subjects, each issue of a Forbes Magazine subscription contains rigorous to the point business analysis that will prove useful to anybody in the business world - from the CEO to an individual investor. Forbes looks at not only national and world economic news, but also social and cultural issues that affect the business and financial worlds, as well as the average person.'),
       (8, 6, 2, 'National Geographic', 4,
        'National Geographic Magazine is your source for information about the world and its people, dealing with the changing universe and man''s involvement in it. Each issue brings you articles by some of today''s best journalists, beautiful photographs from around the world by award-winning photographers, plus interesting full-color maps of various regions. A subscription to National Geographic Magazine brings readers closer to the fascinating people, places, animals, and environments most people rarely get to visit or experience.'),
       (9, 4, 2, 'Sports Illustrated', 2.5,
        'Sports Illustrated Magazine is America’s favorite one-stop source for complete sports coverage and expert analysis. With your Sports Illustrated subscription, you''ll get the latest news stories on professional, college, and amateur athletic events. Subscribe today and stay up-to-date on the latest scores, stats, and late-breaking stories, plus interviews and profiles of your favorite athletes.'),
       (10, 6, 2, 'Car and Driver', 1,
        'Car and Driver Magazine is an excellent source of information for every auto enthusiast. Covering every type of automobile on the road, including cars, pickups, vans, and sport-utility vehicles, both foreign and domestic, Car and Driver Magazine road tests every model and then describes its performance in an objective yet entertaining way to the reader. In addition to car reviews, each issue gives you the news about the latest technology, and developments in the industry and in politics that affect the automobile business. Also included in every Car and Driver magazine subscription are articles on motorsports and important personalities.'),
       (1, 4, 1, 'Batman', 3,
        'Batman is an ongoing American comic book series featuring the DC Comics superhero Batman as its main protagonist. The character first appeared in Detective Comics #27 (cover dated May 1939). Batman proved to be so popular that a self-titled ongoing comic book series began publication with a cover date of Spring 1940. It was first advertised in early April 1940, one month after the first appearance of his new sidekick, Robin, the Boy Wonder. Batman comics have proven to be popular since the 1940s.');

INSERT INTO periodical_issues(periodical_issue_id, periodical_id, issues_name, issue_no, publication_date,
                              issues_description)
VALUES (1, 11, 'I AM GOTHAM Chapter One', 'BATMAN #1', '2019-06-15',
        'No one has ever stopped the Caped Crusader. Not The Joker. Not Two-Face. Not even the entire Justice League. But how does Batman confront a new hero who wants to save the city from the Dark Knight? CANT MISS: Superstar artist David Finch returns to the Dark Knight alongside writer Tom King for this five-part storyline.'),
       (2, 11, 'I AM GOTHAM Chapter Two', 'BATMAN #2', '2019-07-06',
        'In issue #2, after a brush with disaster, Batman struggles to reconcile the fate he could be leaving behind for his city, and reaches out to the idealistic new hero, Gotham. But an evil is building that may overcome both Batman and his new ally.'),
       (3, 11, 'I AM GOTHAM Chapter Three', 'BATMAN #3', '2019-07-20',
        'Batman and Gotham strike out together to get to the bottom of the mysterious attacks against the city. Could this novel and headstrong new hero be everything Gotham City needs...at the cost of the Dark Knight?');

INSERT INTO payments(payment_id, user_id, payment_date, total_price)
VALUES (1, 3, '2018-12-09 11:00:00', '8.70'),
       (2, 3, '2019-12-09 11:00:00', '82.49');

INSERT INTO subscriptions(subscription_id, payment_id, user_id, periodical_id, subscription_plan_id, start_date,
                          end_date)
VALUES (1, 1, 3, 11, 1, '2018-12-09', '2019-01-09'),
       (2, 1, 3, 10, 2, '2018-12-09', '2019-03-09'),
       (3, 2, 3, 6, 1, '2019-12-09', '2020-01-09'),
       (4, 2, 3, 5, 2, '2019-12-09', '2020-03-09'),
       (5, 2, 3, 4, 3, '2019-12-09', '2020-06-09'),
       (6, 2, 3, 3, 4, '2019-12-09', '2020-12-09'),
       (7, 2, 3, 2, 1, '2019-12-09', '2020-01-09'),
       (8, 2, 3, 1, 2, '2019-12-09', '2020-03-09');
