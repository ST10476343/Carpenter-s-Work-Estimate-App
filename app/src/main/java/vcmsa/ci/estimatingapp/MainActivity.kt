package vcmsa.ci.estimatingapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val materialCosts = mapOf(
        R.id.checkWood to 500,
        R.id.checkNails to 20,
        R.id.checkHinges to 30
    )
    private lateinit var taskGroup: RadioGroup
    private lateinit var checkWood: CheckBox
    private lateinit var checkNails: CheckBox
    private lateinit var checkHinges: CheckBox
    private lateinit var etHours: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvDisplay: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        taskGroup = findViewById(R.id.taskGroup)
        checkWood = findViewById(R.id.checkWood)
        checkNails = findViewById(R.id.checkNails)
        checkHinges = findViewById(R.id.checkHinges)
        etHours = findViewById(R.id.etHours)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvDisplay = findViewById(R.id.tvDisplay)

        btnCalculate.setOnClickListener { actCalculate() }
    }
    private fun actCalculate() {
        val selectedTaskId = taskGroup.checkedRadioButtonId

        if (selectedTaskId == -1) {
        }
        val hoursText = etHours.text.toString()
        if (hoursText.isEmpty() || hoursText.toIntOrNull() == null || hoursText.toInt() <= 0) {
        }
        val hours = hoursText.toInt()
        var materialCost = 0

        for ((id, cost) in materialCosts) {
            val checkbox = findViewById<CheckBox>(id)
            if (checkbox.isChecked) {
                materialCost += cost
            }
        }
        if (materialCost == 0) {
            "Please select at least one material"
        }
        val totalCost = materialCost * hours
        tvDisplay.text = "Total Estimated Cost: R$totalCost"
    }
}