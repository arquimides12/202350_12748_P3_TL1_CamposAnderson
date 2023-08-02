package grafo;
import java.util.List;
import java.util.ArrayList;
public class GrafoMatriz {
	int numVerts;
	static int MaxVerts = 20;
	Vertice[] verts;
	int[][] matAd;

	public GrafoMatriz() {
		this(MaxVerts);
	}

	public GrafoMatriz(int mx) {
		matAd = new int[mx][mx];
		verts = new Vertice[mx];
		for (int i = 0; i < mx; i++)
			for (int j = 0; j < mx; j++) // Corrección: j en lugar de i
			matAd[i][j] = 0;
		numVerts = 0;
	}

	// Agregar un nuevo vértice al grafo
	public void nuevoVertice(String nom) {
		boolean esta = numVertice(nom) >= 0;
		if (!esta) {
			Vertice v = new Vertice(nom);
			v.asigVert(numVerts);
			verts[numVerts++] = v;
		}
	}

	// Obtener el índice de un vértice en el arreglo "verts" según su nombre
	int numVertice(String vs) {
		Vertice v = new Vertice(vs);
		boolean encontrado = false;
		int i = 0;
		for (; (i < numVerts) && !encontrado;) {
			encontrado = verts[i].equals(v);
			if (!encontrado)
				i++;
		}
		return (i < numVerts) ? i : -1;
	}

	// Agregar una nueva arista entre dos vértices por su nombre
	public void nuevoArco(String a, String b) throws Exception {
		int va, vb;
		va = numVertice(a);
		vb = numVertice(b);
		if (va < 0 || vb < 0)
			throw new Exception("Vértice no existe");
		matAd[va][vb] = 1;
	}

	// Agregar una nueva arista entre dos vértices por su índice
	public void nuevoArco(int va, int vb) throws Exception {
		if (va < 0 || vb < 0)
			throw new Exception("Vértice no existe");
		matAd[va][vb] = 1;
	}

	// Verificar si dos vértices por su nombre son adyacentes
	public boolean adyacente(String a, String b) throws Exception {
		int va, vb;
		va = numVertice(a);
		vb = numVertice(b);
		if (va < 0 || vb < 0)
			throw new Exception("Vértice no existe");
		return matAd[va][vb] == 1;
	}

	// Verificar si dos vértices por su índice son adyacentes
	public boolean adyacente(int va, int vb) throws Exception {
		if (va < 0 || vb < 0)
			throw new Exception("Vértice no existe");
		return matAd[va][vb] == 1;
	}

	// Obtener la lista de vértices conectados a un vértice por su nombre
	public List<Vertice> nodosConectados(String a) throws Exception {
		int va = numVertice(a);
		if (va < 0) {
			throw new Exception("Vértice no existe");
		}

		List<Vertice> nodosConectados = new ArrayList<>();
		for (int i = 0; i < numVerts; i++) {
			if (matAd[va][i] == 1) {
				nodosConectados.add(verts[i]);
			}
		}

		return nodosConectados;
	}
}
