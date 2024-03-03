-- DROP TABLE public.t_users;
-- DROP SEQUENCE public.user_idx;

CREATE SEQUENCE public.user_idx
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE public.t_users (
	user_idx int8 DEFAULT nextval('user_idx'::regclass) NOT NULL,
	user_id varchar(50) NULL,
	user_password varchar(64) NULL,
	user_join_type varchar(10) NULL,
	user_sns_id varchar(255) NULL,
	user_status bpchar(1) NULL,
	user_nickname varchar(20) NULL,
	user_create_at timestamp NULL,
	CONSTRAINT pk_t_users PRIMARY KEY (user_idx)
);