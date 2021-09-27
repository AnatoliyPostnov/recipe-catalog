create type user_role as enum ('ADMIN', 'USER');

create table registration_user (
    login       varchar     primary key,
    fio         varchar     not null,
    email       varchar     constraint email_format
                            check (email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')
                            not null,
    password    varchar     not null,
    phone       varchar     CONSTRAINT phone_format
                            check (phone ~* '^[7]\d{10}$')
                            not null,
    role        user_role   not null
);

comment on table registration_user is 'Зарегистрированные пользователи';
comment on column registration_user.login is 'Логин пользователя';
comment on column registration_user.fio is 'Полное имя пользователя (фио)';
comment on column registration_user.email is 'email пользователя';
comment on column registration_user.password is 'Пароль пользователя';
comment on column registration_user.phone is 'Телефон пользователя';

create table picture (
                         name        varchar             primary key,
                         path        varchar             not null,
                         extension   varchar             not null
);

comment on table picture is 'Метаинформация о картинке';
comment on column picture.name is 'Название картинки (уникальное)';
comment on column picture.path is 'Абсолютный путь к файлу';
comment on column picture.extension is 'Расширение картинки';

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
    rating       double precision   not null default 0,
    picture_name varchar            references picture(name)
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
comment on column recipe.picture_name is 'Ссылка на картинку';

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

create table category (
    name        varchar             primary key
);

comment on table category is 'Категории рецептов';
comment on column category.name is 'Название категории';

create table category_recipe (
    name        varchar             references category(name) not null,
    recipe_id   bigint              references recipe(id) not null,
    primary key (name, recipe_id)
);

comment on table category_recipe is 'Таблица для связывания таблицы категорий рецептов (category) с таблицей рецептов (recipe) (отношение manyToMany)';
comment on column category_recipe.name is 'Ссылка на таблицу category';
comment on column category_recipe.recipe_id is 'Ссылка на таблицу recipe';

create table ingredient (
    recipe_id   bigint              references recipe(id),
    name        varchar             not null,
    count       varchar             not null,
    primary key (recipe_id, name)
);

comment on table ingredient is 'Таблица, которая содержит названия ингредиентов и их количества для рецептов';
comment on column ingredient.name is 'Название ингредиента';
comment on column ingredient.count is 'Количество ингредиента';

create table cuisine (
    name        varchar             primary key
);

comment on table cuisine is 'Кухня (например итальянская, французская и т.д.)';
comment on column cuisine.name is 'Название кухни';

create table cuisine_recipe (
    name        varchar             references cuisine(name),
    recipe_id   bigint              references recipe(id),
    primary key (name, recipe_id)
);

comment on table cuisine_recipe is 'Смежная таблица, которая реализует соотношение ManyToMany';
comment on column cuisine_recipe.name is 'Ссылка на cuisine';
comment on column cuisine_recipe.recipe_id is 'Ссылка на recipe';

create table step_cooking (
    id              serial              primary key,
    recipe_id       bigint              references recipe(id),
    description     varchar             not null,
    picture_name    varchar             references picture(name)
);

comment on table step_cooking is 'Шаг приготовления';
comment on column step_cooking.id is 'Уникальный идентификатор';
comment on column step_cooking.recipe_id is 'Ссылка на рецепт';
comment on column step_cooking.description is 'Описание шага приготовления';
comment on column step_cooking.picture_name is 'ссылка на картинку';
