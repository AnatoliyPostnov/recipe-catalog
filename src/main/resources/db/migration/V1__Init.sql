create table registration_user (
    login       varchar     primary key,
    fio         varchar     not null,
    email       varchar     constraint email_format
                            check (email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')
                            not null,
    password    varchar     not null,
    phone       varchar     CONSTRAINT phone_format
                            check (phone ~* '^[7]\d{10}$')
                            not null
);

comment on table registration_user is 'Зарегистрированные пользователи';
comment on column registration_user.login is 'Логин пользователя';
comment on column registration_user.fio is 'Полное имя пользователя (фио)';
comment on column registration_user.email is 'email пользователя';
comment on column registration_user.password is 'Пароль пользователя';
comment on column registration_user.phone is 'Телефон пользователя';

create type complexity as enum ('EASILY', 'MEDIUM', 'HARD', 'VERY_HARD');

create table recipe
(
    id           serial             primary key,
    name         varchar            not null,
    description  varchar            not null,
    time_cooking timestamp          not null,
    calories     varchar            not null,
    complexity   complexity         not null,
    cuisine      varchar            not null,
    rating       double precision   not null default 0
);

comment on table recipe is 'Рецепты';
comment on column recipe.id is 'Уникальный идентификатор';
comment on column recipe.name is 'Название рецепта';
comment on column recipe.description is 'Описание';
comment on column recipe.time_cooking is 'Время приготовления';
comment on column recipe.calories is 'Калории';
comment on column recipe.complexity is 'Сложность, котороя может содержать одно из значений типа complexity';
comment on column recipe.cuisine is 'Кухня';
comment on column recipe.rating is 'Рейтинг рецепта';

create table voter (
    recipe_id   bigint              references recipe(id),
    user_login  varchar             references registration_user(login),
    evaluation  double precision    not null,
    primary key (recipe_id, user_login)
);

comment on table voter is 'Голос';
comment on column voter.recipe_id is 'Ссылка на рецепт';
comment on column voter.user_login is 'ссылка на логин пользователя, что ставит оценку рецепту';
comment on column voter.evaluation is 'Значение оценки, которую проставил человек';