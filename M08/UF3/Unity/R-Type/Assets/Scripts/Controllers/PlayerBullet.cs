using UnityEngine;

public class PlayerBullet : MonoBehaviour
{
    public float speed = 10f;
    public float lifetime = 2f;
    public GameObject particleSystemOnHit;

    void Start() {
        Invoke("DestroyObject", lifetime);
    }

    void Update()
    {
        transform.position += speed * Time.deltaTime * transform.right;
    }

    private void OnCollisionEnter2D(Collision2D collision) {
        Vector3 position = collision.collider.ClosestPoint(transform.position);
        Instantiate(particleSystemOnHit, position, Quaternion.identity);
        Destroy(gameObject);
    }

    void DestroyObject() {
        Destroy(gameObject);
    }
}
