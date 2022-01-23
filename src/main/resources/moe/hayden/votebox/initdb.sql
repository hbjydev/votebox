create table if not exists votes (
    id int primary key autoincrement,
    name varchar(64) not null,
    description text(65535) null,
    options varchar(255) not null -- a csv list of options
);

create table if not exists voters (
    id int primary key autoincrement,
    registration varchar(255) not null unique
);

create table if not exists ballots (
    id int primary key autoincrement,
    vote_id int not null,
    voter_id int not null,
    option varchar(255),

    foreign key(vote_id) references votes(id),
    foreign key(voter_id) references voters(id)
);

create table if not exists users (
    id       int primary key autoincrement,
    username text not null unique,
    password text not null,
    role     text check(role in ('admin', 'auditor')) not null
);
