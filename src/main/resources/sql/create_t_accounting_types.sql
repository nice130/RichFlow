-- DROP TABLE public.t_accounting_types;
-- DROP SEQUENCE public.t_accounting_types_seq;

CREATE SEQUENCE public.t_accounting_types_seq
	MINVALUE 0
	NO MAXVALUE
	START 0
	NO CYCLE;

CREATE TABLE public.t_accounting_types (
	act_idx int4 DEFAULT nextval('t_accounting_types_seq'::regclass) NOT NULL,
	user_idx int4 NOT NULL,
	act_parent_idx int4 NULL,
	act_either varchar(1) NULL,
	act_ctg_name varchar(50) NULL,
	act_seq int4 NULL,
	act_create_at timestamp NULL,
	act_update_at timestamp NULL,
	CONSTRAINT pk_t_accounting_types PRIMARY KEY (act_idx)
);
ALTER TABLE public.t_accounting_types ADD CONSTRAINT t_accounting_types_t_users_fk FOREIGN KEY (user_idx) REFERENCES public.t_users(user_idx);