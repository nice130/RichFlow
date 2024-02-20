-- DROP TABLE public.t_accounts;
-- DROP SEQUENCE public.t_accounts_seq;

CREATE SEQUENCE public.t_accounts_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE public.t_accounts (
	ac_idx int4 DEFAULT nextval('t_accounts_seq'::regclass) NOT NULL,
	user_idx int4 NOT NULL,
	ac_level int4 NOT NULL,
	ac_parent_level int4 NOT NULL,
	ac_money_type public.money_type NULL,
	ac_name varchar(50) NULL,
	ac_seq int4 NULL,
	ac_create_at timestamp NULL,
	ac_update_at timestamp NULL,
	CONSTRAINT t_accounts_pk PRIMARY KEY (ac_idx)
);
ALTER TABLE public.t_accounts ADD CONSTRAINT t_accounts_t_users_fk FOREIGN KEY (user_idx) REFERENCES public.t_users(user_idx);