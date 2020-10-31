//Jesús Cencerrado Pedrero

#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

int main()
{
	int casos, montanas; //max = 0;
	//vector <int> mont;
	int a[100000];

	cin >> casos;
	while (casos != 0)
	{
		int max = 0;
		int cont = 0;
			for (int i = 0; i < casos; i++)
			{
				cin >> montanas;
				//mont.push_back(montanas);
				a[i] = montanas;
			}
			//int k = casos - 1;
			//max = mont[k];

			for (int k = casos - 1; k >= 0; k--)
			{
				if (max < a[k])
				{
					max = a[k];
					cont++;
				}
			}
		

		cout << cont << endl;
		cin >> casos;
		//cont = 0;
	}
	

	//system("pause");
	return 0;
}