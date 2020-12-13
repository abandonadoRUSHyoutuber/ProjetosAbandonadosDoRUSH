-- Função para atualizar o número de horas de trabalho do aluno sempre que um horario for inserida/atualizada/deletada.
CREATE OR REPLACE FUNCTION atualizarHorariosAluno() RETURNS trigger AS
$$
DECLARE
horas integer;

BEGIN
   SELECT horassemanais INTO horas FROM public.equipealuno WHERE id = new.equipealuno_id;
   
   IF (TG_OP = 'INSERT') THEN
   horas := horas + new.horasnoturno;
      UPDATE public.equipealuno SET horassemanais = horas where id = new.equipealuno_id;
      RETURN new;
      
   ELSIF (TG_OP = 'UPDATE') THEN
      horas := horas + (old.horasnoturno - new.horasnoturno);
      UPDATE public.equipealuno SET horassemanais = horas where id = new.equipealuno_id;
      RETURN new;
      
   ELSE
      horas := horas - old.horasnoturno;
      UPDATE public.equipealuno SET horassemanais = horas where id = old.equipealuno_id;
      RETURN old;
   END IF;
   
END;
$$
LANGUAGE plpgsql VOLATILE;

-- Função para atualizar o número de horas realizadas do aluno sempre que uma atividade realizada for inserida/atualizada/deletada.
CREATE OR REPLACE FUNCTION atualizarAtividadesRealizadas() RETURNS trigger AS
$$
DECLARE
horas integer;

BEGIN
   SELECT horasrealizadas INTO horas FROM public.equipealuno WHERE id = new.equipealuno_id;
   
   IF (TG_OP = 'INSERT') THEN
      horas := horas + new.horasrealizadas;
      UPDATE public.equipealuno SET horasrealizadas = horas where id = new.equipealuno_id;
      RETURN new;
      
   ELSIF (TG_OP = 'UPDATE') THEN
      horas := horas + (old.horasrealizadas - new.horasrealizadas);
      UPDATE public.equipealuno SET horasrealizadas = horas where id = new.equipealuno_id;
      RETURN new;
      
   ELSE
      horas := horas - old.horasrealizadas;
      UPDATE public.equipealuno SET horasrealizadas = horas where id = old.equipealuno_id;
      RETURN old;
   END IF;
   
END;
$$
LANGUAGE plpgsql VOLATILE;


-- Criando as triggers
CREATE TRIGGER hr_iud BEFORE INSERT OR UPDATE OR DELETE ON public.horarioaluno FOR EACH ROW EXECUTE PROCEDURE atualizarHorariosAluno();
CREATE TRIGGER ar_iud BEFORE INSERT OR UPDATE OR DELETE ON public.atividaderealizada FOR EACH ROW EXECUTE PROCEDURE atualizarAtividadesRealizadas();