package com.hsLink.hslink.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsLink.hslink.R
import com.hsLink.hslink.core.designsystem.theme.HsLinkTheme

@Composable
fun HsLinkTextField(
    value: String,
    placeholder: String,
    onValueChanged: (String) -> Unit,
    borderColor: Color,
    backgroundColor: Color,
    onFocusChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onDoneAction: () -> Unit = {},
    focusRequester: FocusRequester = FocusRequester(),
    isPasswordVisible: Boolean = true,
    singleLine: Boolean = true,
    @DrawableRes leadingIconRes: Int? = null,
) {
    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChanged(newValue)
        },
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp)
            .focusRequester(focusRequester)
            .onFocusChanged { focusState -> onFocusChanged(focusState.isFocused) },
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onDone = { onDoneAction() }
        ),
        textStyle = TextStyle(
            color = HsLinkTheme.colors.Grey700,
            fontStyle = HsLinkTheme.typography.body_16Normal.fontStyle
        ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 13.dp)
                ) {
                    innerTextField()
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = HsLinkTheme.colors.Grey300,
                            style = HsLinkTheme.typography.body_16Normal
                        )
                    }
                }
                leadingIconRes?.let { iconRes ->
                    Icon(
                        imageVector = ImageVector.vectorResource(id = iconRes),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun HsLinkTextFieldPreview() {
    HsLinkTheme {
        HsLinkTextField(
            value = "",
            placeholder = "Placeholder",
            onValueChanged = {},
            borderColor = HsLinkTheme.colors.Grey300,
            backgroundColor = HsLinkTheme.colors.Common,
            onFocusChanged = {},
            leadingIconRes = R.drawable.ic_textfield_edit
        )
    }
}