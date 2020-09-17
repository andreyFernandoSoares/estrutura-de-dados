package vetor;

public class Vetor {
	
	private Aluno[] alunos = new Aluno[100];
	private int totalDeAlunos = 0;
	
	public void add(Aluno aluno) {
		resize();
		this.alunos[totalDeAlunos] = aluno;
	}
	
	public int size() {
		return totalDeAlunos;
	}
	
	public boolean contains(Aluno aluno) {
		for (int i = 0; i < totalDeAlunos; i++) {
			if (aluno.equals(alunos[i])) {
				return true;
			}
		}
		return false;
	}
	
	public Aluno get(int posicao) {
		if (!posicaoOcupada(posicao)) {
			throw new IllegalArgumentException("Posição invalida!");
		}
		return alunos[posicao];
	}

	private boolean posicaoOcupada(int posicao) {
		return posicao >= 0 && posicao < totalDeAlunos;
	}
	
	public void add(int posicao, Aluno aluno) {
		resize();
		if (!posicaoValida(posicao)) {
			throw new IllegalArgumentException("Posição invalida!");
		}
		
		for (int i = totalDeAlunos - 1; i < posicao; i-=1) {
			alunos[i+1] = alunos[i];
		}
		
		alunos[posicao] = aluno;
		totalDeAlunos--;
	}

	private boolean posicaoValida(int posicao) {
		return posicao >= 0 && posicao <= totalDeAlunos;
	}
	
	public void remove(int posicao) {
		for (int i = posicao; i < totalDeAlunos; i++) {
			alunos[i] = alunos[i+1];
		}
		totalDeAlunos--;
	}
	
	public void resize() {
		if(totalDeAlunos == alunos.length) {
			Aluno[] novoArray = new Aluno[alunos.length*2];
			for (int i = 0; i < alunos.length; i++) {
				novoArray[i] = alunos[i];
			}
			alunos = novoArray;
		}
	}
}
