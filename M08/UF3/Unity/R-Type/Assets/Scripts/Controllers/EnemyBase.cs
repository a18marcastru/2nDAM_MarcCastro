using UnityEngine;

public class EnemyBase : MonoBehaviour
{
    [SerializeField] private int _health = 3;
    [SerializeField] private GameObject _explosionPrefab;

    public void GetDamage(int damage) {
        _health -= damage;
        if(_health <= 0) {
            Instantiate(_explosionPrefab, transform.position, Quaternion.identity);
            Destroy(gameObject);
        }
    }

    private void OnCollisionEnter2D(Collision2D collision) {
        GetDamage(1);
    }
}
