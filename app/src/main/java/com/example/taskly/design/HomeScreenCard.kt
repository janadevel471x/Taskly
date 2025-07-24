import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskly.R


@Composable
fun NotesCardView(onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.primary_gray))

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = colorResource(R.color.primary_gray),
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = colorResource(R.color.white)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        modifier = Modifier.size(24.dp),
                        contentDescription = "Checked",
                        tint = colorResource(R.color.time_gray)
                    )
                }

                Spacer(modifier = Modifier.size(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(5.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "janarthanan.s".take(22),
                        color = Color.White,
                        fontSize = 18.sp,
                        maxLines = 1,
                        fontFamily = FontFamily(Font(R.font.lato_regular))
                    )
                    Text(
                        text = "Pravin Raj.s",
                        color = colorResource(R.color.time_gray),
                        fontFamily = FontFamily(Font(R.font.lato_regular))
                    )
                }
            }

            Row (
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){

                Image(
                    modifier = Modifier
                        .padding(5.dp),
                    painter = painterResource(R.drawable.task_ico),
                    contentDescription = "Task image",

                )

                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .border(
                            width = 1.dp,
                            color = colorResource(R.color.purple_200),
                            shape = RoundedCornerShape(2.dp)
                        )
                ) {

                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(R.drawable.flag),
                            contentDescription = "Flag"
                        )
                        Text(
                            text = "1",
                            color = Color.White,
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically),
                            fontFamily = FontFamily(Font(R.font.lato_bold)),
                            fontSize = 8.sp
                        )
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    NotesCardView({})
}