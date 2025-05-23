insert into director(id, avatar, name, nationality, date_of_birth)
values ('31236118-7dbb-4343-adc2-d39a0faceeff',
        'https://www.perfocal.com/blog/content/images/size/w960/2021/01/Perfocal_17-11-2019_TYWFAQ_100_standard-3.jpg',
        'Fede Alvarez',
        'Uruguay',
        '1999-10-04'
        );

insert into director(id, avatar, name, nationality, date_of_birth)
values ('1f71198d-6cc0-41f6-aa4b-7aef693a2923',
        'https://m.media-amazon.com/images/M/MV5BMTA1MDY5ODcxMjBeQTJeQWpwZ15BbWU3MDk0ODUyNDI@._V1_.jpg',
        'David Leitch',
        'Newton, Massachusetts, USA',
        '1979-10-20'
        );

INSERT INTO movie(id, cover_url, title, release_date, lang, duration, genre, plot, director_id)
VALUES ('9ae27569-2e6e-4cb1-84b7-12a2e8958e84',
        'https://m.media-amazon.com/images/M/MV5BMDU0NjcwOGQtNjNjOS00NzQ3LWIwM2YtYWVmODZjMzQzN2ExXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg',
        'Alien: Romolos',
        '2024-07-15',
        'English',
        119,
        'Space Sci-Fi',
        'While scavenging the deep ends of a derelict space station, a group of young space colonists come face to face with the most terrifying life form in the universe.',
        '31236118-7dbb-4343-adc2-d39a0faceeff'
        );

INSERT INTO movie(id, cover_url, title, release_date, lang, duration, genre, plot, director_id)
VALUES ('67ecddeb-87d6-4183-815b-c529a84bb9c8',
        'https://m.media-amazon.com/images/M/MV5BM2U0MTJiYTItMjNiZS00MzU4LTkxYTAtYTU0ZGY1ODJhMjRhXkEyXkFqcGc@._V1_.jpg',
        'The Fall Guy',
        '2024-10-05',
        'English',
        126,
        'Comedy',
        'A stuntman, fresh off an almost career-ending accident, has to track down a missing movie star, solve a conspiracy and try to win back the love of his life while still doing his day job.',
        '1f71198d-6cc0-41f6-aa4b-7aef693a2923'
        );

INSERT INTO movie(id, cover_url, title, release_date, lang, duration, genre, plot, director_id)
VALUES ('3d3cbe96-2308-46d4-a02c-7584c98a50d1',
        'https://m.media-amazon.com/images/M/MV5BMWJiNjJmOWYtMzNmNi00NzM3LTkzYTgtOWMzNTJmMDIyOTVlXkEyXkFqcGc@._V1_.jpg',
        'Atomic Blonde',
        '2017-04-20',
        'English',
        115,
        'Drama',
        'An undercover MI6 agent is sent to Berlin during the Cold War to investigate the murder of a fellow agent and recover a missing list of double agents.',
        '1f71198d-6cc0-41f6-aa4b-7aef693a2923'
        );