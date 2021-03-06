package de.fhg.iais.roberta.syntax.action.nao;

import java.util.List;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.blockly.generated.Field;
import de.fhg.iais.roberta.mode.action.nao.Led;
import de.fhg.iais.roberta.syntax.BlockTypeContainer;
import de.fhg.iais.roberta.syntax.BlocklyBlockProperties;
import de.fhg.iais.roberta.syntax.BlocklyComment;
import de.fhg.iais.roberta.syntax.BlocklyConstants;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.action.Action;
import de.fhg.iais.roberta.transformer.Jaxb2AstTransformer;
import de.fhg.iais.roberta.transformer.JaxbTransformerHelper;
import de.fhg.iais.roberta.visitor.AstVisitor;
import de.fhg.iais.roberta.visitor.NaoAstVisitor;

/**
 * This class represents the <b>naoActions_ledReset</b> block from Blockly into the AST (abstract syntax tree).
 * Object from this class will generate code for resetting all LEDs to their standard state.<br/>
 * <br/>
 */
public final class LedReset<V> extends Action<V> {

    private final Led led;

    private LedReset(Led led, BlocklyBlockProperties properties, BlocklyComment comment) {
        super(BlockTypeContainer.getByName("LED_RESET"), properties, comment);
        this.led = led;
        setReadOnly();
    }

    /**
     * Creates instance of {@link LedReset}. This instance is read only and can not be modified.
     *
     * @param properties of the block (see {@link BlocklyBlockProperties}),
     * @param comment added from the user,
     * @return read only object of class {@link LedReset}
     */
    private static <V> LedReset<V> make(Led led, BlocklyBlockProperties properties, BlocklyComment comment) {
        return new LedReset<V>(led, properties, comment);
    }

    public Led getLed() {
        return this.led;
    }

    @Override
    public String toString() {
        return "LedReset [led=" + this.led + "]";
    }

    @Override
    protected V accept(AstVisitor<V> visitor) {
        return ((NaoAstVisitor<V>) visitor).visitLedReset(this);
    }

    /**
     * Transformation from JAXB object to corresponding AST object.
     *
     * @param block for transformation
     * @param helper class for making the transformation
     * @return corresponding AST object
     */
    public static <V> Phrase<V> jaxbToAst(Block block, Jaxb2AstTransformer<V> helper) {
        List<Field> fields = helper.extractFields(block, (short) 1);
        String leds = helper.extractField(fields, BlocklyConstants.LED);

        return LedReset.make(Led.get(leds), helper.extractBlockProperties(block), helper.extractComment(block));
    }

    @Override
    public Block astToBlock() {
        Block jaxbDestination = new Block();
        JaxbTransformerHelper.setBasicProperties(this, jaxbDestination);
        JaxbTransformerHelper.addField(jaxbDestination, BlocklyConstants.LED, this.led.toString());

        return jaxbDestination;
    }
}
