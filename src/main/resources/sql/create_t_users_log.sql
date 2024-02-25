-- DROP TABLE public.t_users_log;
-- DROP SEQUENCE public.uslg_idx_seq;
CREATE SEQUENCE public.uslg_idx_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE t_users_log (
	uslg_idx	int	default nextval('uslg_idx_seq'::regclass) not null,
	user_idx	int	NOT null,
	uslg_update_ip	varchar(15)	NULL,
	uslg_update_at	timestamp	NULL,
	CONSTRAINT pk_t_users_log PRIMARY KEY (uslg_idx)
);
ALTER TABLE public.t_users_log ADD CONSTRAINT t_users_log_t_users_fk FOREIGN KEY (user_idx) REFERENCES public.t_users(user_idx);