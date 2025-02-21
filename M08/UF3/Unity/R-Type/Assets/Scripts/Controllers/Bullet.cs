using UnityEngine;
using TMPro;

public class Bullets : MonoBehaviour
{
    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.CompareTag("Enemy"))  
        {
            Destroy(gameObject);
        }
    }
}