package br.upf.ads.gestordebolsas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.upf.ads.gestordebolsas.entity.Administrador;
import br.upf.ads.gestordebolsas.entity.Aluno;
import br.upf.ads.gestordebolsas.entity.Curso;
import br.upf.ads.gestordebolsas.entity.Professor;
import br.upf.ads.gestordebolsas.entity.Unidade;
import br.upf.ads.gestordebolsas.util.JPAUtil;

public class CriaBase {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        
        List<Unidade> unidades = new ArrayList<>();
        unidades.add(new Unidade(null, "Instituto de Ciências Biológicas"                             , "ICB" ));
        unidades.add(new Unidade(null, "Instituto de Ciências Exatas e Geociências"                   , "ICEG"));
        unidades.add(new Unidade(null, "Instituto de Filosofia e Ciências Humanas"                    , "IFCH"));
        unidades.add(new Unidade(null, "Faculdade de Artes e Comunicação"                             , "FAC" ));
        unidades.add(new Unidade(null, "Faculdade de Agronomia e Medicina Veterinária"                , "FAMV"));
        unidades.add(new Unidade(null, "Faculdade de Educação"                                        , "FAED"));
        unidades.add(new Unidade(null, "Faculdade de Educação Física e Fisioterapia"                  , "FEFF"));
        unidades.add(new Unidade(null, "Faculdade de Direito"                                         , "FD"  ));
        unidades.add(new Unidade(null, "Faculdade de Ciências Econômicas Administrativas e Contábeis" , "FEAC"));
        unidades.add(new Unidade(null, "Faculdade de Engenharia e Arquitetura"                        , "FEAR"));
        unidades.add(new Unidade(null, "Faculdade de Medicina"                                        , "FM"  ));
        unidades.add(new Unidade(null, "Faculdade de Odontologia"                                     , "FO"  ));
        unidades.forEach((unidade) -> em.merge(unidade)); 
        unidades = em.createQuery("from Unidade ORDER BY id ASC").getResultList();
        
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso(null, "Analise e Desenvolvimento de Sistemas", unidades.get(1)));
        cursos.add(new Curso(null, "Engenharia da computação"             , unidades.get(1)));
        cursos.add(new Curso(null, "Matemática"                           , unidades.get(1)));
        cursos.add(new Curso(null, "Geografia"                            , unidades.get(1)));
        cursos.add(new Curso(null, "Direito"                              , unidades.get(7)));
        cursos.add(new Curso(null, "Nutrição"                             , unidades.get(0)));
        cursos.add(new Curso(null, "Farmácia"                             , unidades.get(0)));
        cursos.add(new Curso(null, "Enfermagem"                           , unidades.get(0)));
        cursos.add(new Curso(null, "Filosofia"                            , unidades.get(2)));
        cursos.add(new Curso(null, "História"                             , unidades.get(2)));
        cursos.add(new Curso(null, "Psicologia"                           , unidades.get(2)));
        cursos.forEach((curso) -> em.merge(curso));
        cursos = em.createQuery("from Curso ORDER BY id ASC").getResultList();

        List<Administrador> administradores = new ArrayList<>();
        administradores.add(new Administrador(null, "080902", "Administrador", "068.139.690-30", "12345678", "admin@admin.admin",        "12345678", "(54) 4 41423293", ""));
        administradores.add(new Administrador(null, "234567", "Eduardo Mior",  "064.941.430-67", "87654321", "eduardo-mior@hotmail.com", "12345678", "(54) 9 91343192", ""));
        administradores.forEach((administrador) -> em.merge(administrador));
        administradores = em.createQuery("from Administrador ORDER BY id ASC").getResultList();

        List<Professor> professores = new ArrayList<>();
        professores.add(new Professor(null, "101224", "Mario Silva",    "485.958.460-06", "09214566", "mario@upf.br",         "mario1516",  "(55) 3 94451234", "", "Mestre", unidades.get(5)));
        professores.add(new Professor(null, "411121", "Jonnas Copag",   "246.320.860-02", "12247567", "jcop@upf.br",          "mypass11",   "(51) 9 31319922", "", "Mestre", unidades.get(4)));
        professores.add(new Professor(null, "919323", "Fabio Dutra",    "854.388.880-86", "49342521", "dutra@upf.br",         "capazq20",   "(59) 9 76658877", "", "Doutor", unidades.get(0)));
        professores.add(new Professor(null, "728111", "Marta Viccenzi", "849.608.600-30", "12342528", "viccenzimarta@upf.br", "itaita2020", "(59) 9 88813100", "", "Doutor", unidades.get(0)));
        professores.forEach((professor) -> em.merge(professor));
        professores = em.createQuery("from Professor ORDER BY id ASC").getResultList();

        List<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno(null, "204591", "Roger M. Brusa",     "483.209.870-59", "09274445", "204591@upf.br", "204591", "(54) 344494448", "", cursos.get(0)));
        alunos.add(new Aluno(null, "102523", "Nardeli M. Stalter", "515.570.680-70", "19373344", "102523@upf.br", "102523", "(51) 996820929", "", cursos.get(0)));
        alunos.add(new Aluno(null, "913456", "Anderson Palenski",  "210.646.670-66", "22979345", "913456@upf.br", "913456", "(54) 981223300", "Andi", cursos.get(0)));
        alunos.add(new Aluno(null, "293344", "Maria Regina",       "148.879.850-88", "57899000", "293344@upf.br", "293344", "(54) 823490012", "", cursos.get(0)));
        alunos.add(new Aluno(null, "359194", "Edgar Maróstica",    "937.949.030-56", "87893911", "359194@upf.br", "359194", "(54) 229765443", "", cursos.get(4)));
        alunos.add(new Aluno(null, "233295", "Cassia Nacimento",   "089.335.610-72", "23245325", "233295@upf.br", "233295", "(54) 233295445", "", cursos.get(4)));
        alunos.forEach((aluno) -> em.merge(aluno));
        alunos = em.createQuery("from Aluno ORDER BY id ASC").getResultList();

        em.getTransaction().commit();
	}

}
