package listaLigada;

public class ListaLigada {
	
	private Celula primeira = null;
	private Celula ultima = null;
	private int totalElementos = 0;

	public void addStart(Object elemento) {
		if (this.totalElementos == 0) {
			Celula nova = new Celula(elemento);
			this.primeira = nova;
			this.ultima = nova;
		} else {
			Celula nova = new Celula(this.primeira, elemento);
			this.primeira.setAnterior(nova);
			this.primeira = nova;
		}
		this.totalElementos++;
	}
	
	public void add(Object elemento) {
		
		if (this.totalElementos == 0) {
			addStart(elemento);
		} else {
			Celula nova = new Celula(elemento);
			this.ultima.setProximo(nova);
			nova.setAnterior(this.ultima);
			this.ultima = nova;
			this.totalElementos ++;
		}
	}
	
	private boolean posicaoOcupada(int posicao) {
		return posicao >= 0 && posicao < totalElementos;
	}
	
	private Celula pegaCelula(int posicao) {
		
		if (!posicaoOcupada(posicao)) {
			throw new IllegalArgumentException("Posicção invalida");
		}
		
		Celula atual = primeira;
		
		for (int i = 0; i < posicao; i++) {
			atual = atual.getProximo();
		}
		
		return atual;
	}
	
	public void add(int posicao, Object elemento) {
		if (posicao == 0) {
			addStart(elemento);
		} else if (posicao == totalElementos) {
			add(elemento);
		} else {
			Celula anterior = this.pegaCelula(posicao -1);
			Celula proxima = anterior.getProximo();
			Celula nova = new Celula(anterior.getProximo(), elemento);
			nova.setAnterior(anterior);
			anterior.setProximo(nova);
			proxima.setAnterior(nova);
			this.totalElementos++;
		}
	}
	
	public Object get(int posicao) {
		return this.pegaCelula(posicao).getElemento();
	}
	
	public void removeDoComeco() {
		if (this.totalElementos == 0) {
			throw new IllegalArgumentException("Lista vazia");
		}
		
		this.primeira = this.primeira.getProximo();
		this.totalElementos--;
		
		if (this.totalElementos == 0) {
			this.ultima = null;
		}
	}
	
	public void removeDoFim() {
		if (totalElementos == 1) {
			removeDoComeco();
		} else {
			Celula penultima = this.ultima.getAnterior();
			penultima.setProximo(null);
			this.ultima = penultima;
			this.totalElementos--;
		}
	}
	
	public void remove(int posicao) {
		if (posicao == 0) {
			removeDoComeco();
		} else if (posicao == this.totalElementos - 1) {
			removeDoFim();
		} else {
			Celula anterior = this.pegaCelula(posicao - 1);
			Celula atual = anterior.getProximo();
			Celula proxima = atual.getProximo();
			
			anterior.setProximo(proxima);
			proxima.setAnterior(anterior);
		}
	}
	
	public int size() {
		return this.totalElementos;
	}
	
	public boolean contains(Object elemento) {
		Celula atual = this.primeira;
		
		while (atual != null) {
			if (atual.getElemento().equals(elemento)) {
				return true;
			}
			
			atual = atual.getProximo();
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		if(this.totalElementos == 0) {
			return "[]";
		} 
		
		Celula atual = primeira;
		
		StringBuilder builder = new StringBuilder("[");
		
		for (int i = 0; i < totalElementos; i++) {
			builder.append(atual.getElemento());
			builder.append(",");
			atual = atual.getProximo();
		}
		
		builder.append("]");
		
		return builder.toString();
	}
}
