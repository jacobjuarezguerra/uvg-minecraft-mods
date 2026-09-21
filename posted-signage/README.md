# Posted Signage

NeoForge mod for Minecraft Java 1.21.1. It adds safety and wayfinding signs as wall-placeable blocks. Right-click a vertical wall face to install one; it drops as an item when broken or when its supporting block is removed.

The mod also includes a connecting sansevieria planter with exact textured foliage on two crossed X planes and a flat ten-wide by four-high T-shaped six-figure mural.

The restroom collection uses double-sided 3D signs that project perpendicular to the wall, with a raised frame, wall plate, and support brackets. The other safety signs remain thin wall panels.

## Items

- `posted_signage:customizable_hanging_sign`
- `posted_signage:customizable_hanging_sign_green`
- `posted_signage:customizable_hanging_sign_red`
- `posted_signage:customizable_hanging_sign_yellow`
- `posted_signage:customizable_hanging_sign_light_blue`
- `posted_signage:customizable_hanging_sign_purple`
- `posted_signage:customizable_hanging_sign_orange`
- `posted_signage:right_arrow_sign`
- `posted_signage:fire_extinguisher_sign`
- `posted_signage:emergency_exit_sign`
- `posted_signage:do_not_use_elevator_sign`
- `posted_signage:emergency_stairs_sign`
- `posted_signage:sansevieria_planter`
- `posted_signage:statue_relief`

### Decorative models

- Place two sansevieria planters next to each other to join them into one continuous two-block planter. Each planter accepts only one partner, and the remaining half returns to its single model when the other half is removed.
- Place the mural from the lower-left cell of its four-wide center stem on a sturdy ten-wide by four-high wall area. One item installs all 28 occupied parts of the T, and breaking a part removes the connected mural.

### Restroom hanging signs

The men's, women's, and mixed designs are available in the original blue plus green, red, orange, yellow, and light blue, for 18 restroom signs total.

Base IDs:

- `posted_signage:mens_restroom_sign`
- `posted_signage:womens_restroom_sign`
- `posted_signage:mixed_restroom_sign`

Colored variants append one of these suffixes to the base ID:

- `_green`
- `_red`
- `_orange`
- `_yellow`
- `_light_blue`

All items are available from the **Posted Signage** creative tab and can also be obtained with `/give`.

## Customizable CIT hanging sign

The customizable signs follow the wide white CIT reference design and support different text on each side. Seven separate items provide blue, green, red, yellow, light blue, purple, and orange CIT panels.

- Place it against a vertical wall.
- Right-click either broad face with an empty hand to edit that face's four text lines.
- Select the desired color variant from the creative tab or with `/give`.
- The white background and CIT mark are part of the sign texture; only the central room-name lettering is editable.
- The main panel remains white and the editable lettering remains dark for readability.
- Glow ink and honeycomb retain the usual vanilla sign behavior.

## Build

Requires a Java 21 toolchain.

```powershell
.\validate_assets.ps1
.\gradlew.bat build
```

The distributable mod JAR is created under `build/libs/`.
