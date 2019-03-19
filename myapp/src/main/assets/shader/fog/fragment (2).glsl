uniform sampler2D vTexture;

varying float fogFactor;
void main (void)
{
    vec4 fogColor    = vec4(0.0,0.4,0.6,1.0);
    vec4 finalColor = texture2D(vTexture, gl_TexCoord[0].xy);
    gl_FragColor     = mix(fogColor, finalColor, fogFactor );        //根据雾的权重与finalColor 做混合操作
}