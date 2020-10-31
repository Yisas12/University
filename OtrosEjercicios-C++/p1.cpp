#include <iostream>
#include <vector>
using namespace std;

typedef struct {
	bool cauc;
	int par;
}tPack;

tPack caucasico(const vector<int> &v, int ini, int fin) {
	int mitad = (fin - ini) >> 1;
	tPack m1, m2;

	if ((fin - ini) == 3) {
		m1.par = 0;
		m1.cauc = false;

		for (int i = ini; i <= fin; i++)
		{
			if (v[i] % 2 == 0) m1.par++;
		}
	}
	else {
		m1 = caucasico(v, ini, mitad);
		m2 = caucasico(v, mitad + 1, fin);

		if (m1.cauc && m2.cauc) {
			if (abs(m1.par - m2.par) < 3) {
				m1.cauc = false;
			}
			else m1.par += m2.par;
		}
		else {
			m1.cauc = false;
		}
	}

	return m1;
}

bool resuelveCaso() {
	int n, nums;
	vector <int> v;
	tPack pack;

	cin >> n;
	if (n > 0) {
		for (int i = 0; i < n; i++)
		{
			cin >> nums;
			v.push_back(nums);
		}
		if (n <= 4) cout << "SI\n";
		else {
			pack = caucasico(v, 0, n-1);

			if (pack.cauc) cout << "SI\n";
			else cout << "NO\n";
		}
		return true;
	}
	else return false;
}

int main()
{
	while (resuelveCaso());

	system("pause");
	return 0;
}